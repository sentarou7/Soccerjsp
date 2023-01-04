package to.msn.wings.Soccerjsp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CheckUtil {
    private ArrayList<String> _errs = null;

    public CheckUtil() {
        this._errs = new ArrayList<String>();
    }

    public void requiredCheck(String value, String name) {
        if (value == null || value.isBlank()) {
            this._errs.add(name + "は必須入力です。");
        }
    }

    public void lengthCheck(String value, int max, String name) {
        if (value != null && !value.isBlank()) {
            if (value.length() > max) {
                this._errs.add(name + "は" + max + "文字以下で入力してください。");
            }
        }
    }

    public void numberTypeCheck(String value, String name) {
        if (value != null && !value.isBlank()) {
            try {
                Integer.parseInt(value);
            } catch (NumberFormatException e) {
                this._errs.add(name + "は数値で入力してください。");
            }
        }
    }

    public void rangeCheck(String value, int max, int min, String name) {
        if (value != null && !value.isBlank()) {
            int val = 0;
            try {
                val = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                this._errs.add(name + "は数値で入力してください。");
            }
            if (val < min || val > max) {
                this._errs.add(name + "は" + min + "以上、かつ" + max + "以下で入力してください。");
            }
        }
    }

    public void dateTypeCheck(String value, String name) {
        if (value != null && !value.isBlank()) {
            // if(value.matches("^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$")) {
            try {
                LocalDate.parse(value);
            } catch (DateTimeException e) {
                this._errs.add(name + "は正しい日付で入力してください。");
            }
            // }else{
            // this._errs.add(name + "は日付形式で入力してください。");
            // }
        }
    }

    public void regExCheck(String value, String pattern, String name) {
        if (value != null && !value.isBlank()) {
            if (!value.matches(pattern)) {
                this._errs.add(name + "を正しい形式で入力してください。");
            }
        }
    }

    public void duplicateCheck(String value, String sql, String name) {
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Soccerjsp");
            try (Connection db = ds.getConnection()) {
                PreparedStatement ps = db.prepareStatement(sql);
                ps.setString(1, value);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    this._errs.add(name + "が重複しています。");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public boolean hasErrors() {
        return !this._errs.isEmpty();
    }

    public List<String> getError() {
        return Collections.unmodifiableList(this._errs);
    }
}