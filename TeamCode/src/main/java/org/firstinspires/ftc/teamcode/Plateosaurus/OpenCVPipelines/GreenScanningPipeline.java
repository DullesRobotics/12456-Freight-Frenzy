package org.firstinspires.ftc.teamcode.Plateosaurus.OpenCVPipelines;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Libraries.AddOns.EasyOpenCV;
import org.firstinspires.ftc.teamcode.Libraries.AddOns.Pipeline;
import org.firstinspires.ftc.teamcode.Libraries.Logger;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
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
        Imgproc.rectangle(input, new Rect(new Point(currentZone.minX,0), new Point(currentZone.maxX, EasyOpenCV.VIEWPORT_HEIGHT)), new Scalar(124, 255, 0), 5);
        return input;
    }

    private Zone detectCapstone(Mat input) {
        Zone zone;

        double[] numerators = new double[]{0,0,0};
        double[] denominators = new double[]{0,0,0};

        for(int x = 0; x < EasyOpenCV.VIEWPORT_WIDTH-1; x+=2){
            for(int y = (EasyOpenCV.VIEWPORT_HEIGHT/3) * 2; y < EasyOpenCV.VIEWPORT_HEIGHT-1; y+=2){
                Zone currentZone = x < Zone.LEFT.maxX ? Zone.LEFT :
                        x < Zone.MIDDLE.maxX ? Zone.MIDDLE : Zone.RIGHT;
                denominators[currentZone.index]++;

                // figure out if rgb is green
                double[] rgb = input.get(y,x);
                if(rgb[0] < 150 && rgb[1] > 180 && rgb[2] < 150)
                    numerators[currentZone.index] ++;
            }
        }

        double[] averages = new double[]{numerators[0] / denominators[0], numerators[1] / denominators[1], numerators[2] / denominators[2]};
        System.out.println("avgs: " + averages[0] + " " + averages[1] + " " + averages[2]);
        double highestAvg = Math.max(averages[0], Math.max(averages[1], averages[2]));
        if(highestAvg == averages[0])
            zone = Zone.LEFT;
        else if(highestAvg == averages[1])
            zone = Zone.MIDDLE;
        else
            zone = Zone.RIGHT;

        return zone;
    }

    public void


    @Override
    public void updateLog(Logger l) {
      //  l.putData("Object Visible", isObjectVisible());
        l.putData("Zone: ", currentZone.name());
    }

    enum Zone {
        LEFT(0, EasyOpenCV.VIEWPORT_WIDTH / 3, 0),
        MIDDLE(EasyOpenCV.VIEWPORT_WIDTH / 3 + 1, 2 * (EasyOpenCV.VIEWPORT_WIDTH / 3), 1),
        RIGHT(2 * (EasyOpenCV.VIEWPORT_WIDTH / 3) + 1,EasyOpenCV.VIEWPORT_WIDTH, 2);

        private int minX, maxX, index;

        Zone (int minX, int maxX, int index){
            this.maxX = maxX;
            this.minX = minX;
            this.index = index;
        }
    }
}