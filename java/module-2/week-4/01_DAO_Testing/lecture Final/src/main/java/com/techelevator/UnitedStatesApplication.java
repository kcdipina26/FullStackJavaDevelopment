package com.techelevator;

import com.techelevator.dao.JdbcParkDao;
import com.techelevator.dao.ParkDao;
import com.techelevator.model.Park;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

public class UnitedStatesApplication {

    private final ParkDao parkDao;

    public UnitedStatesApplication(DataSource dataSource) {
        this.parkDao = new JdbcParkDao(dataSource);
    }

    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/UnitedStatesTesting");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        UnitedStatesApplication application = new UnitedStatesApplication(dataSource);
        application.run();

    }

    private void run() {
//        Park park = parkDao.getParkById(1);
//        System.out.println(park);
//
//        List<Park> parks = parkDao.getParksByState("AA");
//        for(Park p : parks){
//            System.out.println(p);
//        }
//
//        Park park = parkDao.createPark(new Park(0,"new park", LocalDate.now(), 777, true ));
//        System.out.println(park);

//        park.setParkName("new name");
//        park.setArea(777);
//        park.setDateEstablished(LocalDate.now());
//        park.setHasCamping(false);
//        park = parkDao.updatePark(park);
//        System.out.println(park);

//        parkDao.deleteParkById(1);
//
//        park = parkDao.getParkById(1);
//        System.out.println(park);



        parkDao.unlinkParkState(2, "AA");
    }

}
