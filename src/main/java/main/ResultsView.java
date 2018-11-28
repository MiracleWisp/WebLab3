package main;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="resultsTable")
public class ResultsView implements Serializable {

    private List<Point> points;

    @PostConstruct
    public void init() {
        points = new ArrayList<Point>();
        points.add(new Point(4,5,6, true));
    }

    public List<Point> getPoints() {
        return points;
    }
}
