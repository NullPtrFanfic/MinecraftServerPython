@EventBusSubscriber
public class RegistryHandler {
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Item item : ItemInit.ITEMS) {
            if (item instanceof IHasModel) {
                ((IHasModel)item).registerModels();
            }
        }
    }

    // Здесь могут быть другие методы, связанные с регистрацией предметов, блоков и других объектов
}
