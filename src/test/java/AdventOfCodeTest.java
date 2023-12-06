import day_01.Trebuchet;
import day_02.CubeConundrum;
import day_03.GearRatios;
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

    @Test
    public void dayThreePartOne() throws IOException {
        GearRatios gearRatios = new GearRatios();
        int total = gearRatios.partOne(gearRatios.getInputText());
        System.out.println("Total : " + total);
    }

    @Test
    public void dayThreePartTwo() throws IOException {
        GearRatios gearRatios = new GearRatios();
        int total = gearRatios.partTwo(gearRatios.getInputText());
        System.out.println("Total : " + total);
    }

}
