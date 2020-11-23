package cn.novice.entity;

public class FrameDept {
    private Integer deptid;

    private String deptname;

    private String deptshortname;

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }

    public String getDeptshortname() {
        return deptshortname;
    }

    public void setDeptshortname(String deptshortname) {
        this.deptshortname = deptshortname == null ? null : deptshortname.trim();
    }
}