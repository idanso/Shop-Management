package observer;

public interface ObservableShop {
	void addCostumerObserver(ObserverCostumers o);
	void deleteCostumerObserver(ObserverCostumers o);
	void sendNewsToCostumer(ObservableShop shop, String discountMassage);
	public void addCostumerNameRecivedMassage (String name);
}

//void addObserver(Observer o);
//void deleteObserver(Observer o);
//void notifyObservers(HomeWork hw);
