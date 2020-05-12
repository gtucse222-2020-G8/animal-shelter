package cse222.group8.server;

import java.util.List;
import java.util.Queue;
import cse222.group8.server.DataStructures.*;

public class System {

    public class Admin{

        void addShelter() {}

        void removeShelter() {}

        void changeShelterCap() {}

    }
    private BinarySearchTree<City> cities;
    private BinarySearchTree<User> users;
    private Queue<CapacityChangeRequest> capacityChangeRequests;
    private Queue<ShelterRequest> newShelterRequests;
    private Queue<ShelterRequest> removeShelterRequests;

    /*
    shelter ekle
    shelter kapasite değişecek
    shelter updatele
    shelter sil
    getshelter(il, ilçe, shelter name)
    user ekle
    user updatele
    user sil
    animal ekle
    animal değiştir
    animal sil
    task ekle
    task updatele
    task sil
    hayvan hastalık gir -> push
    hayvan hastalık sil -> pop
    adoption request ekle (shelter, animal, user)
    adoption request sil (shelter, request)
     */

}