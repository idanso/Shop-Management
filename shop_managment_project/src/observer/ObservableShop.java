package observer;

public interface ObservableShop {
	void addCostumerObserver(ObserverCostumers o);
	void deleteCostumerObserver(ObserverCostumers o);
	void sendNewsToCostumer(String discountMassage);
	public void addCostumerNameRecivedMassage (String name);
}
