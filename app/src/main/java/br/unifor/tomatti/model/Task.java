package br.unifor.tomatti.model;

/**
 * Created by chico on 01/12/16.
 */

public class Task implements ModelInterface {

    private Long id;
    private String name;
    private String description;
    private String pomodoros;

    public Task() {}

    public Task(String name, String description, String pomodoros) {
        this(null, name, description, pomodoros);
    }

    public Task(Long id, String name, String description, String pomodoros) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pomodoros = pomodoros;
    }

    @Override
    public Long getId() {
        return null;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPomodoros() {
        return pomodoros;
    }

    public void setPomodoros(String pomodoros) {
        this.pomodoros = pomodoros;
    }


}
