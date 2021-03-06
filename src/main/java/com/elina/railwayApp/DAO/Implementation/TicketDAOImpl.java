package com.elina.railwayApp.DAO.Implementation;

import com.elina.railwayApp.DAO.TicketDAO;
import com.elina.railwayApp.model.Schedule;
import com.elina.railwayApp.model.Seat;
import com.elina.railwayApp.model.Ticket;
import com.elina.railwayApp.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class TicketDAOImpl<E extends Ticket> extends GenericDAOImpl<E> implements TicketDAO<E> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Ticket> getBySchedule(Schedule schedule) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket where schedule = :schedule")
                .setParameter("schedule", schedule)
                .getResultList();
    }

    @Override
    public List<Ticket> findSameUserOnTrain(User user, Schedule schedule) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket " +
                        "where schedule = :schedule and user = :user")
                .setParameter("schedule", schedule)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public Ticket findTicketByScheduleAndSeat(Schedule schedule, Seat seat) {
        return (Ticket) sessionFactory.getCurrentSession()
                .createQuery("from Ticket " +
                        "where schedule = :schedule and seat = :seat")
                .setParameter("schedule", schedule)
                .setParameter("seat", seat)
                .uniqueResult();
    }

    @Override
    public List<Ticket> getByUser(User user) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket " +
                        "where user = :user")
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public List<Ticket> getByDate(Date date) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket " +
                        "where date(schedule.dateDeparture) = :date")
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Ticket> getByDates(Date dateFrom, Date dateTo) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket " +
                        "where schedule.dateDeparture between :dateFrom and :dateTo " +
                        "and schedule.dateArrival between :dateFrom and :dateTo")
                .setParameter("dateFrom", dateFrom)
                .setParameter("dateTo", dateTo)
                .getResultList();
    }
}
