package observer;

public interface ObservableShop {
	void addCostumerObserver(ObserverCostumers o);
	void deleteCostumerObserver(ObserverCostumers o);
	void sendNewsToCostumers(String discountMassage);
	public void addCostumerNameRecivedMassage (String name);
}
