package com.noasking.opencv.compare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Range;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * Created by MaJing on 2018/1/24.
 */
public class CompareUtils {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        try {
            Mat source = Imgcodecs.imread("D:\\var\\imgsplit\\powerAbility1_4.jpg");
            Mat target = Imgcodecs.imread("D:\\var\\imgsplit\\powerAbility1_42.jpg");
            Mat target2 = Imgcodecs.imread("D:\\var\\imgsplit\\powerAbility1_3.jpg");

            Mat sourceHist = getHistogram(source);
            Mat targetHist = getHistogram(target);
            Mat target2Hist = getHistogram(target2);

            //算出直方圖比對,for 6種method
            for (int i = 0; i <= 5; i++) {
                double result0 = Imgproc.compareHist(sourceHist, sourceHist, i);
                double result1 = Imgproc.compareHist(sourceHist, targetHist, i);
                double result2 = Imgproc.compareHist(sourceHist, target2Hist, i);
                // double result3=Imgproc.matchShapes(src_contours.get(0), trg3_contours.get(0),  1,0);

                System.out.println("i= " + i);
                System.out.println("result0= " + result0);
                System.out.println("result1= " + result1);
                System.out.println("result2= " + result2);
                System.out.println("================================");
            }

            System.out.println("Imgproc.CV_COMP_CORREL= " + Imgproc.CV_COMP_CORREL); // 0 1.0
            System.out.println("Imgproc.CV_COMP_CHISQR= " + Imgproc.CV_COMP_CHISQR); //
            System.out.println("Imgproc.CV_COMP_BHATTACHARYYA= " + Imgproc.CV_COMP_BHATTACHARYYA);
            System.out.println("Imgproc.CV_COMP_HELLINGER= " + Imgproc.CV_COMP_HELLINGER);
            System.out.println("Imgproc.CV_COMP_INTERSECT= " + Imgproc.CV_COMP_INTERSECT);
            System.out.println("Imgproc.CV_COMP_CHISQR_ALT= " + Imgproc.CV_COMP_CHISQR_ALT);
            System.out.println("Imgproc.CV_COMP_KL_DIV= " + Imgproc.CV_COMP_KL_DIV);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public static Mat getHistogram(Mat source) {

        Mat HSVsource = new Mat();
        //BGR轉換到HSV colorspace
        Imgproc.cvtColor(source, HSVsource, Imgproc.COLOR_BGR2HSV);

        Mat hist = new Mat();
        int Hbins = 30;
        int Sbins = 32;

        //對Hue channel使用30個bins,對Saturation chhannel使用32個bins
        MatOfInt mHistSize = new MatOfInt(Hbins, Sbins);

        //對Hue的取值Range(0,256),Saturation取值Range:(0,180)
        MatOfFloat mRanges = new MatOfFloat(0, 180, 0, 256);

        //使用chnnel0 及channel1
        MatOfInt mChannels = new MatOfInt(0, 1);

        List<Mat> Lhsv = Arrays.asList(HSVsource);

        //計算HSV色彩空間的直方圖
        Imgproc.calcHist(Lhsv, mChannels, new Mat(), hist, mHistSize, mRanges, false);
        Core.normalize(hist, hist, 0, 255, Core.NORM_MINMAX, -1, new Mat());

        return hist;
    }

}
