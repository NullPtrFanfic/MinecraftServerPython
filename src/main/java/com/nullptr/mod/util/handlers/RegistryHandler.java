@EventBusSubscriber

public class RegistryHandler 

{

    @SubscribeEvent

    public static void onItemRegister(RegistryEvent.Register<Item> event)

    {

        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));

    }

    

    @SubscribeEvent

    public static void onModelRegister(ModelRegistryEvent event)

    {

        for(Item item : ItemInit.ITEMS)

        {

            if(item instanceof IHasModel)

            {

                ((IHasModel)item).registerModels();

            }

        }

    }

    

    public static void preInitRegistries()

    {

        

    }

    

    public static void initRegistries()

    {

        

    }

    

    public static void postInitRegistries()

    {

        

    }

    

    public static void serverRegistries(FMLServerStartingEvent event)

    {

        

    }

}

