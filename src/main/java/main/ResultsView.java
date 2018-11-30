package main;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class ResultsView implements Serializable {

    private float x;
    private float y;
    private float r;
    private String sid;

    private List<Point> points;

    private Point newPoint;

    @PostConstruct
    public void init() {
        points = new ArrayList<Point>();
        r = 1;
        x = 0;
        y = 0;
        FacesContext fctx = FacesContext.getCurrentInstance();
        sid = fctx.getExternalContext().getSessionId(false);
        System.out.println("sid: " + sid);
    }

    public int addPoint() {
        newPoint = new Point(x, y, r);
        int errCode;
        float[] right_Rs = {1f, 2f, 3f, 4f, 5f};
        float[] right_Xs = {-2.0f, -1.5f, -1.0f, -0.5f, 0.0f, 0.5f, 1.0f, 1.5f, 2.0f};

        errCode = 1; // wrong R
        for (float _r : right_Rs) {
            if (_r == r) {
                errCode = 0;
                break;
            }
        }

        if (errCode != 0) return errCode;

        errCode = 2; // wrong X
        for (float _x : right_Xs) {
            if (_x == x) {
                errCode = 0;
                break;
            }
        }

        if (errCode != 0) return errCode;

        if ((y < -5) || (y > 3))
            errCode = 3; // wrong Y

        if (errCode != 0) return errCode;


        newPoint.checkArea();
        points.add(newPoint);
        x = 0;
        y = 0;
        return errCode;
    }

    public List<Point> getPoints() {
        return points;
    }

    public Point getNewPoint() {
        return newPoint;
    }

    public void setNewPoint(Point newPoint) {
        this.newPoint = newPoint;
    }

    public float getX() {
        System.out.println("X=" + this.x);
        return x;
    }

    public void setX(float x) {
        this.x = x;
        System.out.println("X=" + this.x);
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }
}
