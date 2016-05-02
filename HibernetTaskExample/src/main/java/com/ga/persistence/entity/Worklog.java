/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ga.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NIRAJ
 */
@Entity
@Table(name = "worklog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Worklog.findAll", query = "SELECT w FROM Worklog w"),
    @NamedQuery(name = "Worklog.findByWorkLogId", query = "SELECT w FROM Worklog w WHERE w.workLogId = :workLogId"),
    @NamedQuery(name = "Worklog.findByWorkLogTaskId", query = "SELECT w FROM Worklog w WHERE w.taskId.taskId = :taskId"),
    @NamedQuery(name = "Worklog.findByCreatedDate", query = "SELECT w FROM Worklog w WHERE w.createdDate = :createdDate")})
public class Worklog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    //@Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WorkLog_Id")
    private Integer workLogId;
    @Lob
    @Column(name = "StartTime")
    private String startTime;
    @Lob
    @Column(name = "TotalHours")
    private String totalHours;
    @Lob
    @Column(name = "TotalMinutes")
    private String totalMinutes;
   
    @Column(name = "CreatedDate")
    private String createdDate;
    
    @JoinColumn(name = "Task_Id", referencedColumnName = "Task_Id")
    @ManyToOne
    private Task taskId;
    @JoinColumn(name = "User_Id", referencedColumnName = "User_Id")
    @ManyToOne
    private User userId;

    public Worklog() {
    }

    public Worklog(Integer workLogId) {
        this.workLogId = workLogId;
    }

    public Integer getWorkLogId() {
        return workLogId;
    }

    public void setWorkLogId(Integer workLogId) {
        this.workLogId = workLogId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(String totalHours) {
        this.totalHours = totalHours;
    }

    public String getTotalMinutes() {
        return totalMinutes;
    }

    public void setTotalMinutes(String totalMinutes) {
        this.totalMinutes = totalMinutes;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Task getTaskId() {
        return taskId;
    }

    public void setTaskId(Task taskId) {
        this.taskId = taskId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (workLogId != null ? workLogId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Worklog)) {
            return false;
        }
        Worklog other = (Worklog) object;
        if ((this.workLogId == null && other.workLogId != null) || (this.workLogId != null && !this.workLogId.equals(other.workLogId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Worklog [workLogId=" + workLogId + ", startTime=" + startTime + ", totalHours=" + totalHours
                + ", totalMinutes=" + totalMinutes + ", createdDate=" + createdDate + ", taskId=" + taskId
                + ", userId=" + userId + "]";
    }
    
}
