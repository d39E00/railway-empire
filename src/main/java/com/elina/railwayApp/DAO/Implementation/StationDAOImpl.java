package com.elina.railwayApp.DAO.Implementation;

import com.elina.railwayApp.DAO.StationDAO;
import com.elina.railwayApp.model.Station;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StationDAOImpl implements StationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Station findById(Long id) {
        return (Station) sessionFactory.getCurrentSession()
                .createQuery("from Station where id = :id")
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public void add(Station station) {
        sessionFactory.getCurrentSession()
                .save(station);
    }

    @Override
    public void delete(Long id) {
        Station station = findById(id);
        sessionFactory.getCurrentSession()
                .remove(station);
    }

    @Override
    public void update(Station station) {
        sessionFactory.getCurrentSession()
                .update(station);
    }

    @Override
    public List<Station> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Station")
                .getResultList();
    }
}