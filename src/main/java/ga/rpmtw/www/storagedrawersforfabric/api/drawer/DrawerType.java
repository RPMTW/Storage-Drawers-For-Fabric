package ga.rpmtw.www.storagedrawersforfabric.api.drawer;

import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;

public enum DrawerType implements StringIdentifiable
{

    FULL("full", "border"), HALF("half", "half_border"), QUAD("quad", "quad_border");

    public static EnumProperty<DrawerType> DRAWER_TYPE = EnumProperty.of("drawer_type", DrawerType.class);

    private String name;
    private String assetsPath;

    DrawerType(String name, String assetsPath)
    {
        this.name = name;
        this.assetsPath = assetsPath;
    }

    @Override
    public String asString()
    {
        return name;
    }

    public String getAssetsPath()
    {
        return assetsPath;
    }

}
