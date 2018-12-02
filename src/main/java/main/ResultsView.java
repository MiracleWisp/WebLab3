package main;

import org.postgresql.ds.PGConnectionPoolDataSource;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@ManagedBean
@SessionScoped
public class ResultsView implements Serializable {

    private float x;
    private float y;
    private float r;
    private String sid = "kek";

    private List<Point> points;

    private Point newPoint;

    private Connection dbConnection;

    @PostConstruct
    public void init() {
        Properties props = new Properties();
        final PGConnectionPoolDataSource dataSource = new PGConnectionPoolDataSource();
        try {
            //обращаемся к файлу и получаем данные
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
            props.load(inputStream);

            dataSource.setUser(props.getProperty("DB_USER"));
            dataSource.setDatabaseName(props.getProperty("DB_NAME"));
            dataSource.setPassword(props.getProperty("DB_PWD"));
            dataSource.setServerName(props.getProperty("DB_HOST"));
            dataSource.setPortNumber(Integer.parseInt(props.getProperty("DB_PORT")));

            dbConnection = dataSource.getConnection();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        r = 1;
        x = 0;
        y = 0;
        FacesContext fctx = FacesContext.getCurrentInstance();
        sid = fctx.getExternalContext().getSessionId(false);
        System.out.println("sid: " + sid);
        points = selectPoints();
    }

    public int addPoint() {
        newPoint = new Point(x, y, r);
//        System.out.println("x = ["+ x +"], " + "y = ["+ y +"], " + "r = ["+ r +"]");
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


        if ((x < -2) || (x > 2))
            errCode = 2; // wrong X


        if ((y < -5) || (y > 3))
            errCode = 3; // wrong Y

        if (errCode != 0) {
            newPoint = null;
            addMessage("You can't put point here");
            return errCode;
        }

        newPoint.checkArea();
        points.add(newPoint);
        try {
            insertPoint(newPoint);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        return x;
    }

    public void setX(float x) {
        x = fromContext(x, "x_value");
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        y = fromContext(y, "y_value");
        this.y = y;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        r = fromContext(r, "r_value");
        this.r = r;
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private float fromContext(float x, String name) {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        String x_value = params.get(name);
        if (x_value != null) x = Float.parseFloat(x_value);
        return x;
    }

    public ArrayList<Point> selectPoints() {
        ArrayList<Point> points = new ArrayList<Point>();
        try {
            PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM points WHERE sid = ?");
            statement.setString(1, sid);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Point point = new Point(rs.getFloat("x"), rs.getFloat("y"), rs.getFloat("r"), rs.getBoolean("success"));
                points.add(point);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return points;
    }


    private void insertPoint(Point point) throws SQLException {
        PreparedStatement statement = dbConnection.prepareStatement("INSERT INTO points VALUES (?, ?, ?, ?, ?)");
        statement.setDouble(1, point.getX());
        statement.setDouble(2, point.getY());
        statement.setDouble(3, point.getR());
        statement.setString(4, sid);
        statement.setBoolean(5, point.isSuccess());
        statement.execute();
    }
}
