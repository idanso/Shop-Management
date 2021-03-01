package observer;

public interface ObservableShop {
	void addCostumerObserver(ObserverCostumers o);
	void deleteCostumerObserver(ObserverCostumers o);
<<<<<<< HEAD
	void sendNewsToCostumer(String discountMassage);
=======
	void sendNewsToCostumers(String discountMassage);
>>>>>>> 4bdd3e284696db8a8527b6b2e1bb0d5c6199378c
	public void addCostumerNameRecivedMassage (String name);
}
