package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.DAO.StatusDAO;
import com.elina.railwayApp.DAO.TrainDAO;
import com.elina.railwayApp.DTO.TrainDTO;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.exception.ErrorCode;
import com.elina.railwayApp.model.Seat;
import com.elina.railwayApp.model.Status;
import com.elina.railwayApp.model.Train;
import com.elina.railwayApp.service.TrainService;
import lombok.extern.log4j.Log4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j
@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainDAO trainDAO;

    @Autowired
    private StatusDAO statusDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public void add(TrainDTO trainDTO) throws BusinessLogicException {
        Train trainCreating = getByName(trainDTO.getTrainName());
        if (trainCreating != null)
            throw new BusinessLogicException(ErrorCode.TRAIN_NOT_UNIQUE.getMessage());

        if (trainDTO.getCntCarriage() < 0 || trainDTO.getCntCarriage() > 25 && trainDTO.getCntSeats() < 0)
            throw new BusinessLogicException(ErrorCode.WRONG_PARAMETERS_FOR_SEATS.getMessage());

        Set<Seat> seats = new HashSet<>();
        Train train = new Train();
        Status status = statusDAO.getByName("NOT_USED");
        train.setStatus(status);
        train.setName(trainDTO.getTrainName());
        for (int i = 1; i <= trainDTO.getCntCarriage(); i++) {
            for (int j = 1; j <= trainDTO.getCntSeats(); j++) {
                Seat seat = new Seat();
                seat.setCarriage(i);
                seat.setSeat(j);
                seat.setTrain(train);
                seats.add(seat);
            }
        }
        train.setSeats(seats);
        trainDAO.add(train);
    }

    @Override
    @Transactional
    public void delete(Long id) throws BusinessLogicException {
        Train train = getById(id);
        if (train == null
                || !train.getStatus().getStatusName().equals("NOT_USED"))
            throw new BusinessLogicException(ErrorCode.NULL_ELEMENTS.getMessage());

        Status status = statusDAO.getByName("DELETED");
        train.setStatus(status);
        trainDAO.update(train);
        log.info("TRAIN WAS REMOVED");

    }

    @Override
    @Transactional
    public void update(Train train) {
        Status status = statusDAO.getByName("NOT_USED");
        train.setStatus(status);
        trainDAO.update(train);
    }

    @Override
    @Transactional
    public List<TrainDTO> getAll() {
        List<Train> trains = trainDAO.getAll();
        return trains.stream()
                .map(x -> modelMapper.map(x, TrainDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Train getById(Long id) {
        return trainDAO.getById(id);
    }

    @Override
    @Transactional
    public Train getByName(String name) {
        return trainDAO.getByName(name);
    }
}
