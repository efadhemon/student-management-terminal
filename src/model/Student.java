package model;

public class Student {
    private int id;
    private String name;
    private String program;
    private int batch;
    private float cgpa;
    private String password;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student(int id, String name, String program, int batch, float cgpa) {
        this.id = id;
        this.name = name;
        this.program = program;
        this.batch = batch;
        this.cgpa = cgpa;
    }


    public Student(int id, String name, String program, int batch, float cgpa, String password) {
        this.id = id;
        this.name = name;
        this.program = program;
        this.batch = batch;
        this.cgpa = cgpa;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int branch) {
        this.batch = branch;
    }

    public float getCgpa() {
        return cgpa;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

    public String toDBString() {
        return id + ","+name+ ","+program+","+batch+","+cgpa+","+password+"\n";
    }

    public void printInfo (){
        String info = id + ","+name+ ","+program+","+batch+","+cgpa+","+password;

        System.out.println(info);
    }
}
