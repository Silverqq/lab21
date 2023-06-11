import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String groupName);
}

class User implements Observer {
    private final String name;
    private final List<String> subscribedGroups;

    public User(String name) {
        this.name = name;
        subscribedGroups = new ArrayList<>();
    }

    public void subscribe(String groupName) {
        subscribedGroups.add(groupName);
    }

    public void update(String groupName) {
        System.out.println("Пользователь " + name + " Получил новое уведомление от " + groupName);
    }

}

interface Subject {
    void registerObserver(Observer observer);

    void notifyObservers(String groupName);
}

class Group implements Subject {
    private final String name;
    private final List<Observer> observers;

    public Group(String name) {
        this.name = name;
        observers = new ArrayList<>();
    }

    public void post(String message) {
        System.out.println("Новый пост из группы " + name + ": " + message);
        notifyObservers(name);
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String groupName) {
        for (Observer observer : observers) {
            observer.update(groupName);
        }
    }
}

public class ObserverPatternDemo {

    public static void main(String[] args) {

        // create users
        User user1 = new User("Чекушин Никита");
        User user2 = new User("Марков Максим");

        // create groups
        Group group1 = new Group("Терновка");
        Group group2 = new Group("Пенза Live");
        Group group3 = new Group("Спутник Live");
        Group group4 = new Group("Парк Белинского");

        // register observers
        user1.subscribe("Терновка");
        user1.subscribe("Пенза Live");
        user1.subscribe("Спутник Live");

        user2.subscribe("Терновка");
        user2.subscribe("Пенза Live");
        user2.subscribe("Спутник Live");
        user2.subscribe("Парк Белинского");

        group1.registerObserver(user1);
        group2.registerObserver(user1);
        group3.registerObserver(user1);
        group1.registerObserver(user2);
        group2.registerObserver(user2);
        group3.registerObserver(user2);
        group4.registerObserver(user2);

        // notify observers
        group1.post("Реконструкция моста почти завершена!!!");
        group3.post("Салют в честь города!!!");
        group2.post("Городу исполняется 360 лет!!!");
        group4.post("Скоро будет реконструкция Планетария!!!");
    }
}