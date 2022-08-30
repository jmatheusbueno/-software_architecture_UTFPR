package com.mycompany.gerenciadordetarefas;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MainGerenciadorDeTarefas { 
    private final Scanner Scanner;
    private final List<GerenciadorDeTarefasModel> Tasks = new ArrayList();

    public MainGerenciadorDeTarefas(Scanner scanner){
        this.Scanner = scanner;
    }
    
    public void ShowMenuOptions() {
        Scanner scanner = this.Scanner;
        int loopIndex = 0;
        boolean showMenu = true;
        
        do {
            System.out.println("\n[1] - Criar Nova Tarefa\r\n[2] - Editar Tarefa\r\n[3] - Sair");
            int userOptionSelected = Scanner.nextInt();
            
            switch (userOptionSelected) {
                case 1:
                    System.out.println("\nInforme o nome da tarefa:");
                    this.Tasks.add(new GerenciadorDeTarefasModel().Factory(loopIndex, scanner.next()));
                    break;
                case 2:
                    this.UpdateTask(this.GetTask());
                    break;
                case 3:
                    showMenu = false;
            }
            this.ShowTasks();
            loopIndex++;
        } while (showMenu);
    }
       
    private GerenciadorDeTarefasModel GetTask(){
        System.out.println("Informe o número da tarefa:");
        int taskIndex = Scanner.nextInt();
        
        return Tasks.get(taskIndex);
    }
    
    private void UpdateTask(GerenciadorDeTarefasModel task) {
        System.out.println("[1] - Alterar Nome\r\n[2] - Alterar Status");
        int userOptionSelected = Scanner.nextInt();
        
        if (userOptionSelected == 1) {
            System.out.println("Digite o nome:");
            task.setName(Scanner.next());
        }
        else 
            ChangeTaskStatus(task);
        
    }
    
    private void ChangeTaskStatus(GerenciadorDeTarefasModel task){
        System.out.println("[1] - Criado\r\n[2] - Em Progresso\r\n[3] - Concluida");
        int statusSelected = Scanner.nextInt();
        switch (statusSelected) {
            case 1:
                task.setStatus(EGerenciadorDeTarefasStatus.CREATED);
                break;
            case 2:
                task.setStatus(EGerenciadorDeTarefasStatus.IN_PROGRESS);
                break;
            default:
                task.setStatus(EGerenciadorDeTarefasStatus.DONE);
                break;
        }
    }
    
    private void ShowTasks(){
        List<GerenciadorDeTarefasModel> tasksCloned = new ArrayList(Tasks);
        Stream<GerenciadorDeTarefasModel> tasksCreated = tasksCloned.stream().filter(f -> f.getStatus() == EGerenciadorDeTarefasStatus.CREATED);
        Stream<GerenciadorDeTarefasModel> tasksInProgress = tasksCloned.stream().filter(f -> f.getStatus() == EGerenciadorDeTarefasStatus.IN_PROGRESS);
        Stream<GerenciadorDeTarefasModel> tasksDone = tasksCloned.stream().filter(f -> f.getStatus() == EGerenciadorDeTarefasStatus.DONE);

        System.out.println("\nTarefas Criadas");
        tasksCreated.forEach(f -> System.out.println("["+f.getId()+"] - " + f.getName()));
        System.out.println("Tarefas em Progresso");
        tasksInProgress.forEach(f -> System.out.println("["+f.getId()+"] - " + f.getName()));
        System.out.println("Tarefas Concluídas");
        tasksDone.forEach(f -> System.out.println("["+f.getId()+"] - " + f.getName()));
    }   
}
