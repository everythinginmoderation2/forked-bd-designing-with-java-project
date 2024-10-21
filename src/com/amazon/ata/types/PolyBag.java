package com.amazon.ata.types;

import java.math.BigDecimal;
import java.util.Objects;

public class PolyBag extends Packaging {
    private BigDecimal volume;
    private Material material;
    /**
     * Instantiates a new Packaging object.
     *
     * @param material - the Material of the package
     */
    public PolyBag(Material material, BigDecimal volume) {
        super(material);
        this.material = material;
        this.volume = volume;
    }

    private BigDecimal getVolume() {
        return volume;
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public BigDecimal getMass() {
        return BigDecimal.valueOf(Math.ceil(Math.sqrt(volume.doubleValue() * 0.6)));
    }

    @Override
    public boolean canFitItem(Item item) {
//        throw new UnsupportedOperationException();
        BigDecimal itemVolume = item.getLength().multiply(item.getWidth()).multiply(item.getHeight());
        return this.volume
                .compareTo(itemVolume) > 0;
    }

    @Override
    public boolean equals(Object o) {
        // Can't be equal to null
        if (o == null) {
            return false;
        }

        // Referentially equal
        if (this == o) {
            return true;
        }

        // Check if it's a different type
        if (getClass() != o.getClass()) {
            return false;
        }

        PolyBag that = (PolyBag) o;
        return getMaterial() == that.getMaterial() &&
                Objects.equals(getVolume(), that.getVolume());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMaterial(), getVolume());
    }
}
