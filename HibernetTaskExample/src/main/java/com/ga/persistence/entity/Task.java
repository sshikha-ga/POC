/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ga.persistence.entity;

import java.io.Serializable;
import java.util.Collection;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author NIRAJ
 */
@Entity
@Table(name = "task")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t Order By t.createdDate Desc"),
        @NamedQuery(name = "Task.findByTaskId", query = "SELECT t FROM Task t WHERE t.taskId = :taskId") })
public class Task implements Serializable {
    @OneToMany(mappedBy = "taskId")
    private Collection<Worklog> worklogCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Task_Id")
    private Integer taskId;
    @Lob
    @Column(name = "Title")
    private String title;
    @Lob
    @Column(name = "Description")
    private String description;
    @Lob
    @Column(name = "StartDate")
    private String startDate;
    @Lob
    @Column(name = "EndDate")
    private String endDate;
    @Lob
    @Column(name = "CreatedDate")
    private String createdDate;
    @JoinColumn(name = "CreatedBy", referencedColumnName = "User_Id")
    @ManyToOne
    private User createdBy;

    public Task() {
    }

    public Task(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskId != null ? taskId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.taskId == null && other.taskId != null) || (this.taskId != null && !this.taskId.equals(other.taskId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Task [worklogCollection=" + worklogCollection + ", taskId=" + taskId + ", title=" + title
                + ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate
                + ", createdDate=" + createdDate + ", createdBy=" + createdBy + "]";
    }

    @XmlTransient
    public Collection<Worklog> getWorklogCollection() {
        return worklogCollection;
    }

    public void setWorklogCollection(Collection<Worklog> worklogCollection) {
        this.worklogCollection = worklogCollection;
    }

}
