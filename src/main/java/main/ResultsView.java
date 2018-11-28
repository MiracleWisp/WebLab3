package main;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "resultsTable")
@ViewScoped
public class ResultsView implements Serializable {

    private List<Point> points;

    @PostConstruct
    public void init() {
        points = new ArrayList<Point>();
        Point p = new Point(0.7f, 0.5f, 1);
        p.checkArea();
        points.add(p);
    }

    public void addPoint(float userX, float userY, float userR) {
        Point point = new Point(userX, userY, userR);

        int errCode;
        float[] right_Rs = {1f, 2f, 3f, 4f, 5f};
        float[] right_Xs = {-2.0f, -1.5f, -1.0f, -0.5f, 0.0f, 0.5f, 1.0f, 1.5f, 2.0f};

        errCode = 1; // wrong R
        for (float r : right_Rs) {
            if (r == userR) {
                errCode = 0;
                break;
            }
        }

        errCode = 2; // wrong X
        for (float x : right_Xs) {
            if (x == userX) {
                errCode = 0;
                break;
            }
        }

        if ((userY < -5) || (userY > 3))
            errCode = 3; // wrong Y

        //Ну и тут что-то сделать с ними



        point.checkArea();
        points.add(point);
    }

    public List<Point> getPoints() {
        return points;
    }
}
