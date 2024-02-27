package entity.object.interaction;


import java.util.HashMap;
import java.util.Map;


public class ObjectInteractionFactory {
    private final Map<String, ObjectInteraction> interactionMap;

    public ObjectInteractionFactory() {
        interactionMap = new HashMap<>();
        interactionMap.put("Key", new KeyInteraction());
        interactionMap.put("Boots", new BootsInteraction());
        interactionMap.put("Chest", new ChestInteraction());
        interactionMap.put("Door", new DoorInteraction());
    }

    public ObjectInteraction getInteraction(String objectType) {
        return interactionMap.getOrDefault(objectType, null);
    }
}
