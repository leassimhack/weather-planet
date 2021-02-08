package com.exam.utils;

import com.exam.service.model.Planet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.geom.Point2D;
import java.util.List;

import static com.exam.factory.ApiFactory.createCoordinateMatrix;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UtilTest {

    private static final double ANGLE_RADIANS = -8.726646259971647;

    private Util Util;

    @BeforeEach
    public void initObjects() {

        Util = new Util();

    }

    @Test
    public void convertInRadians_returnAngleInRadians() {

        double angleRadians = Util.convertInRadians(-500);

        assertEquals(ANGLE_RADIANS, angleRadians);

    }

    @Test
    public void createPosition_returnPositionXY() {

        final Point2D position = Util.createPosition(ANGLE_RADIANS, 500);

        assertEquals(-383.022221559489, position.getX());
        assertEquals(-321.39380484327, position.getY());

    }

    @Test
    public void createPlanets_returnPositionXY() {

        final List<Planet> finalPlanets = Util.createPlanets();

        assertNotNull(finalPlanets);
        assertEquals(3, finalPlanets.size());

    }

    @Test
    public void isTriangle_returnTrue() {

        final boolean isTriangle =  Util.isTriangle(createCoordinateMatrix());

        assertTrue(isTriangle);

    }

}
