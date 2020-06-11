import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class SILab2Test {

    private List<String> allUsers(String... elems){
        return new ArrayList<String>();
    }

    @Test
    void everyStatementTest() {

        RuntimeException ex;

        //        user = null ; users = any users
        ex = assertThrows(RuntimeException.class,
                ()-> SILab2.function(null,allUsers("bojan","stojan","mojan")));
        assertTrue(ex.getMessage().contains("The user argument is not initialized!"));
        //System.out.println("The user argument is not initialized!");


//        user = username:null, password: any, email: any ; users = any users
        ex = assertThrows(RuntimeException.class,
                ()-> SILab2.function(new User(null,"pw","mail"),allUsers("bojan","stojan","mojan")));
        assertTrue(ex.getMessage().contains("User already exists!"));
        //System.out.println("User already exists!");


        //3. user = username:bojan, password: any, email:any ; users = bojan stojan kojan

        assertFalse(SILab2.function(new User("bojan","pw","mail"), allUsers("bojan","stojan","kojan")));

//        user = username:bojan, password: any, email: null ; users= bojan stojan kojan
        assertFalse(SILab2.function(new User("bojan","pw",null), allUsers("bojan", "stojan", "mojan")));
        //System.out.println("user = username:bojan, password: any, email: null ; users= bojan stojan kojan\n!");


//        user=username:bojan, password: any, email: mail@.com; users= bojan stojan kojann
        assertTrue(SILab2.function(new User("bojan","pw","mail@.com"), allUsers("bojan", "stojan", "mojan")));
        //System.out.println("user=username:bojan, password: any, email: mail@.com; users= bojan stojan kojann\n");



//        user=username:bojan, password: any, email: mail@com; users= bojan stojan kojann
        assertFalse(SILab2.function(new User("bojan","pw","mail@com"), allUsers("bojan", "stojan", "mojan")));
        //System.out.println("user=username:bojan, password: any, email: mail@com; users= bojan stojan kojann\n");


    }


//            1,2 - 3 - 22
//            1,2 -4-5-6-20-22
//            1,2-4-5-7-8-9-19-20-22

//            1,2-4-5-7-8-10-(11.1-11.2-12-13-14-15-16-11.3-11.2)-17-19-20-21-22
//            1,2-4-5-7-8-10-(11.1-11.2-12-13-16-11.3-11.2)-17-18-19-20-22

//            1,2-4-5-7-8-10-(11.1-11.2-14-15-16-11.3-11.2)-17-18-19-20-22
//            1,2-4-5-7-8-10-(11.1-11.2-16-11.3-11.2)-17-18-19-20-22


    @Test
    void everyPathTest(){
        RuntimeException ex;
        //            1,2 - 3 - 22
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(null,allUsers("abc","cba")));
        assertTrue(ex.getMessage().contains("The user argument is not initialized!"));

//            1,2 -4-5-6-20-22
        ex = assertThrows(RuntimeException.class,
                ()-> SILab2.function(new User(null,"pw","mail"),allUsers("bojan","stojan","mojan")));
        assertTrue(ex.getMessage().contains("User already exists!"));



//            1,2-4-5-7-8-9-19-20-22
        assertFalse(SILab2.function(new User("bojan","pw",null), allUsers("bojan", "stojan", "mojan")));
        //System.out.println("user = username:bojan, password: any, email: null ; users= bojan stojan kojan\n!");


//            1,2-4-5-7-8-10-11.1-11.2-12-13-14-15-16-11.3-11.2-17-19-20-21-22
        assertTrue(SILab2.function(new User("bojan","pw","mail@.com"), allUsers("bojan", "stojan", "mojan")));
        //System.out.println("user=username:bojan, password: any, email: mail@.com; users= bojan stojan kojann\n");



//            1,2-4-5-7-8-10-11.1-11.2-12-13-16-11.3-11.2-17-18-19-20-21

        assertFalse(SILab2.function(new User("bojan","pw","mail@com"), allUsers("bojan", "stojan", "mojan")));
        //System.out.println("user=username:bojan, password: any, email: mail@com; users= bojan stojan kojann\n");


//            1,2-4-5-7-8-10-11.1-11.2-14-15-16-11.3-11.2-17-18-19-20-22    -- cannot happen because atChar must be true to go into 15.

//            1,2-4-5-7-8-10-(11.1-11.2-16-11.3-11.2)-17-18-19-20-22        -- ako nema majmunce i nema tocka
        assertFalse(SILab2.function(new User("bojan","pw","mailcom"), allUsers("bojan", "stojan", "mojan")));

//          1,2-4-5-7-8-10-(11.1-11.2-12-14-16-11.3-11.2)-17-18-19-20-22
        // ako nema majmunce a ima tocka
        assertFalse(SILab2.function(new User("bojan","pw","mail.com"), allUsers("bojan", "stojan", "mojan")));





    }
}