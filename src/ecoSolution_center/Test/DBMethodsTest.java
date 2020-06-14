package ecoSolution_center.Test;

import ecoSolution_center.Data.DBMethods;
import org.junit.Test;

import static org.junit.Assert.*;

public class DBMethodsTest {

    @Test
    public void selectLaundryItems() {

        DBMethods test = new DBMethods();
        test.selectLaundryItems(2);
    }
}