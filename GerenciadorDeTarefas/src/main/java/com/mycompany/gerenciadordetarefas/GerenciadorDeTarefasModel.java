package com.mycompany.gerenciadordetarefas;

public class GerenciadorDeTarefasModel {
    private EGerenciadorDeTarefasStatus Status;    
    private String Name;
    private int Id;
    
    public GerenciadorDeTarefasModel Factory(int id, String name) {
        this.Id = id;
        this.Status = EGerenciadorDeTarefasStatus.CREATED;
        this.Name = name;
        return this;
    }  
    
    public int getId() {
        return this.Id;
    }
    
    public EGerenciadorDeTarefasStatus getStatus(){
        return this.Status;
    }
    
    public String getName(){
        return this.Name;
    }
    
    public void setStatus(EGerenciadorDeTarefasStatus status){
        this.Status = status;
    }
    
    public void setName(String name){
        this.Name = name;
    }
}
