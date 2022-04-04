package org.firstinspires.ftc.teamcode.Plateosaurus.OpenCVPipelines;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Libraries.AddOns.Pipeline;
import org.firstinspires.ftc.teamcode.Libraries.Logger;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

@Config
public class GreenScanningPipeline extends OpenCvPipeline implements Pipeline {


    public int minThreshold = 155;
    public int maxThreshold = 255;
    public static int coi = 3;
  //  private Mat yCrCB;
    private Mat channel;
    private Mat threshold;
    private List<MatOfPoint> contours;

    private Rect rect;
    private Zone currentZone;

    public GreenScanningPipeline() {
        //yCrCB = new Mat();
    }

    @Override
    public Mat processFrame(Mat input) {
    currentZone = detectCapstone(input);
        return input;
    }

    private Zone detectCapstone(Mat input) {
        Zone zone;

        int avgNumZone1, avgNumZone2, avgNumZone3;
        int avgDenZone1, avgDenZone2, avgDenZone3;

        // do double for loop, one for rows, one for columns
        // loop through every pixel and get green value: input.get(0,1)[1];
        // increment a number for the avg denomniator for that pixel's zone,
        // and add the green value to another variable for the avg numerator for that pixrl's zone
        // once you've done all the pixels do the avg division

        // determine zone with highest avg and return it

        return zone;
    }


    @Override
    public void updateLog(Logger l) {
      //  l.putData("Object Visible", isObjectVisible());
        l.putData("Zone: ", currentZone.name());
    }

    enum Zone {
        LEFT(0, 106),
        MIDDLE(107, 212),
        RIGHT(213,320);

        private int minX, maxX;

        Zone (int minX, int maxX){
            this.maxX = maxX;
            this.minX = minX;
        }
    }
}