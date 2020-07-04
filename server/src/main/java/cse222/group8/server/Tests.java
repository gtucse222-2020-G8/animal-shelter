package cse222.group8.server;

import java.util.Date;

public class Tests {
    public static void main(String[] args){
        testAll();
    }

    public static void testAll(){
        shelterAddCat();
        shelterAddDog();
        shelterDeleteAnimal();
        shelterAddDiseasedAnimal();
        shelterAddMultipleDiseasedAnimals();
        shelterApproveAdoptionRequest();
        shelterAddTask();
        shelterApproveTask();
        shelterUpdateTask();
        shelterDeleteTask();
    }
    public static void shelterAddCat(){
        System.out.println("Testing add cat functionality of shelter");
        ShelterSystem system = new ShelterSystem();
        City city = new City("testCity",1);
        Town town = new Town("testTown",city);
        city.getTowns().add(town);
        system.getCitiesBST().add(city);
        Shelter testShelter = town.addShelter("testShelter",10,10,"asd","+90","pass");
        Animal cat = new Animal("cat1","catKind",4,true,testShelter);
        testShelter.addCat(cat);
        if(testShelter.getCatSize()==1){
            for(Animal _cat : testShelter.getCats()){
                if(_cat.getName().equals(cat.getName())){
                    System.out.println("Passed!");
                    return;
                }
            }
        }
        System.out.println("Failed!");
        System.exit(-1);
    }
    public static void shelterAddDog(){
        System.out.println("Testing add dog functionality of shelter");
        ShelterSystem system = new ShelterSystem();
        City city = new City("testCity",1);
        Town town = new Town("testTown",city);
        city.getTowns().add(town);
        system.getCitiesBST().add(city);
        Shelter testShelter = town.addShelter("testShelter",10,10,"asd","+90","pass");
        Animal dog = new Animal("dog1","catKind",4,false,testShelter);
        testShelter.addDog(dog);
        if(testShelter.getDogSize()==1){
            for(Animal _dog : testShelter.getDogs()){
                if(_dog.getName().equals(dog.getName())){
                    System.out.println("Passed!");
                    return;
                }
            }
        }
        System.out.println("Failed!");
        System.exit(-1);
    }
    public static void shelterDeleteAnimal(){
        System.out.println("Testing delete animal functionality of shelter");
        ShelterSystem system = new ShelterSystem();
        City city = new City("testCity",1);
        Town town = new Town("testTown",city);
        city.getTowns().add(town);
        system.getCitiesBST().add(city);
        Shelter testShelter = town.addShelter("testShelter",10,10,"asd","+90","pass");
        Animal dog = new Animal("dog1","catKind",4,false,testShelter);
        testShelter.addDog(dog);
        testShelter.removeDog(dog);
        if(testShelter.getDogSize()==0){
            System.out.println("Passed!");
            return;
        }
        System.out.println("Failed!");
        System.exit(-1);

    }
    public static void shelterAddDiseasedAnimal(){
        System.out.println("Testing add diseased animal functionality of shelter");
        ShelterSystem system = new ShelterSystem();
        City city = new City("testCity",1);
        Town town = new Town("testTown",city);
        city.getTowns().add(town);
        system.getCitiesBST().add(city);
        Shelter testShelter = town.addShelter("testShelter",10,10,"asd","+90","pass");
        Animal cat = new Animal("cat1","catKind",4,true,testShelter);
        testShelter.addCat(cat);
        for(Animal _cat : testShelter.getCats()){
            testShelter.addDiseasedAnimal(_cat.getId(),1);
        }
        if(testShelter.getDiseasedAnimals().size()==1) {
            if (testShelter.getDiseasedAnimals().poll().getAnimal().getName().equals(cat.getName())) {
                if(testShelter.getDiseasedAnimals().size()==0) {
                    System.out.println("Passed!");
                    return;
                }
            }
        }
        System.out.println("Failed!");
        System.exit(-1);
    }
    public static void shelterAddMultipleDiseasedAnimals(){
        System.out.println("Testing add diseased animal's priority functionality of shelter");
        ShelterSystem system = new ShelterSystem();
        City city = new City("testCity",1);
        Town town = new Town("testTown",city);
        city.getTowns().add(town);
        system.getCitiesBST().add(city);
        Shelter testShelter = town.addShelter("testShelter",10,10,"asd","+90","pass");
        Animal cat = new Animal("cat1","catKind",4,true,testShelter);
        testShelter.addCat(cat);
        Animal cat2 = new Animal("cat2","catKind",4,true,testShelter);
        testShelter.addCat(cat2);
        int diseaseLevel = 1;
        for(Animal _cat : testShelter.getCats()){
            testShelter.addDiseasedAnimal(_cat.getId(),diseaseLevel);
            diseaseLevel+=5;
        }
        if(testShelter.getDiseasedAnimals().size()==2) {
            Disease disease = testShelter.getDiseasedAnimals().poll();
            if (disease.getAnimal().getName().equals(cat2.getName()) && disease.getPriority()==6) {
                System.out.println("Passed!");
                return;
            }
        }
        System.out.println("Failed!");
        System.exit(-1);
    }
    public static void shelterApproveAdoptionRequest(){
        System.out.println("Testing approve adoption request functionality of shelter");
        ShelterSystem system = new ShelterSystem();
        City city = new City("testCity",1);
        Town town = new Town("testTown",city);
        city.getTowns().add(town);
        system.getCitiesBST().add(city);
        Shelter testShelter = town.addShelter("testShelter",10,10,"asd","+90","pass");
        Animal cat = new Animal("cat1","catKind",4,true,testShelter);
        testShelter.addCat(cat);
        Animal cat2 = new Animal("cat2","catKind",4,true,testShelter);
        testShelter.addCat(cat2);
        User user = new User("name1","usnm","pass1",city,town,new Date());
        system.addUser(user);
        user.createARequest(cat);
        if(testShelter.getAdoptionRequests().size()==1){
            if(user.getRequests().size()==1){
                if(testShelter.approveAdoptionRequest(0)){
                    if(testShelter.getAdoptionRequests().size()==0 && user.getRequests().size()==0){
                        System.out.println("Passed!");
                        return;
                    }
                }
            }
        }
        System.out.println("Failed!");
        System.exit(-1);
    }
    public static void shelterAddTask(){
        System.out.println("Testing create task functionality of shelter");
        ShelterSystem system = new ShelterSystem();
        City city = new City("testCity",1);
        Town town = new Town("testTown",city);
        city.getTowns().add(town);
        system.getCitiesBST().add(city);
        Shelter testShelter = town.addShelter("testShelter",10,10,"asd","+90","pass");
        Task task = new Task("task1",false);
        testShelter.addTask(task);
        if(testShelter.getTasks().size()==1){
            if(testShelter.getTasks().get(0).getTask() == task.getTask()){
                Task task2 = new Task("task2",false);
                testShelter.addTask(task2);
                if(testShelter.getTasks().size()==2){
                    System.out.println("Passed!");
                    return;
                }
            }
        }
        System.out.println("Failed!");
        System.exit(-1);
    }
    public static void shelterApproveTask(){
        System.out.println("Testing approve task functionality of shelter");
        ShelterSystem system = new ShelterSystem();
        City city = new City("testCity",1);
        Town town = new Town("testTown",city);
        city.getTowns().add(town);
        system.getCitiesBST().add(city);
        Shelter testShelter = town.addShelter("testShelter",10,10,"asd","+90","pass");
        Task task = new Task("task1",false);
        testShelter.addTask(task);
        testShelter.getTasks().get(0).setStatus(true);
        if(testShelter.getTasks().get(0).getStatus()){
            System.out.println("Passed!");
            return;
        }
        System.out.println("Failed!");
        System.exit(-1);
    }
    public static void shelterUpdateTask(){
        System.out.println("Testing update task functionality of shelter");
        ShelterSystem system = new ShelterSystem();
        City city = new City("testCity",1);
        Town town = new Town("testTown",city);
        city.getTowns().add(town);
        system.getCitiesBST().add(city);
        Shelter testShelter = town.addShelter("testShelter",10,10,"asd","+90","pass");
        Task task = new Task("task1",false);
        testShelter.addTask(task);
        testShelter.getTasks().get(0).setTask("newtasktext");
        if(testShelter.getTasks().get(0).getTask().equals("newtasktext")){
            System.out.println("Passed!");
            return;
        }
        System.out.println("Failed!");
        System.exit(-1);
    }
    public static void shelterDeleteTask(){
        System.out.println("Testing delete task functionality of shelter");
        ShelterSystem system = new ShelterSystem();
        City city = new City("testCity",1);
        Town town = new Town("testTown",city);
        city.getTowns().add(town);
        system.getCitiesBST().add(city);
        Shelter testShelter = town.addShelter("testShelter",10,10,"asd","+90","pass");
        Task task = new Task("task1",false);
        testShelter.addTask(task);
        Task task2 = new Task("task2",false);
        testShelter.addTask(task2);
        testShelter.getTasks().remove(0);
        if(testShelter.getTasks().size()==1){
            if(testShelter.getTasks().get(0).getTask().equals(task2.getTask())) {
                System.out.println("Passed!");
                return;
            }
        }
        System.out.println("Failed!");
        System.exit(-1);
    }
}
