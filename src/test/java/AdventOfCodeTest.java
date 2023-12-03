import day_01.Trebuchet;
import day_02.CubeConundrum;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AdventOfCodeTest {

    @Test
    public void dayOnePartOne() throws IOException {
        Trebuchet trebuchet = new Trebuchet();
        int total = trebuchet.partOne(trebuchet.getInputText());
        System.out.println("Total : " + total);
    }

    @Test
    public void dayOnePartTwo() throws IOException {
        Trebuchet trebuchet = new Trebuchet();
        int total = trebuchet.partTwo();
        System.out.println("Total : " + total);
    }

    @Test
    public void dayTwoPartOne() throws IOException {
        CubeConundrum cubeConundrum = new CubeConundrum();
        int total = cubeConundrum.partOne(cubeConundrum.getInputText());
        System.out.println("Total : " + total);
    }

    @Test
    public void dayTwoPartTwo() throws IOException {
        CubeConundrum cubeConundrum = new CubeConundrum();
        int total = cubeConundrum.partTwo(cubeConundrum.getInputText());
        System.out.println("Total : " + total);
    }

}
