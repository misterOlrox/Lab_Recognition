import java.util.stream.Collectors

class Mushroom {
    int id
    boolean edible

    String capShape
    String capSurface
    String capColor
    String bruises
    String odor
    String gillAttachment
    String gillSpacing
    String gillSize
    String gillColor
    String stalkShape
    String stalkRoot
    String stalkSurfaceAboveRing
    String stalkSurfaceBelowRing
    String stalkColorAboveRing
    String stalkColorBelowRing
    String veilType
    String veilColor
    String ringNumber
    String ringType
    String sporePrintColor
    String population
    String habitat

    List<Integer> attributeVector

    List<Integer> getAttributeVector() {
        if (attributeVector == null) {
            List<Boolean> booleanList = new ArrayList<>()

            booleanList.add(capShape              == 'CONVEX')
            booleanList.add(capSurface            == 'SMOOTH')
            booleanList.add(capColor              == 'WHITE')
            booleanList.add(bruises               == 'BRUISES')
            booleanList.add(odor                  == 'ALMOND')
            booleanList.add(gillAttachment        == 'FREE')
            booleanList.add(gillSpacing           == 'CROWDED')
            booleanList.add(gillSize              == 'NARROW')
            booleanList.add(gillColor             == 'WHITE')
            booleanList.add(stalkShape            == 'TAPERING')
            booleanList.add(stalkRoot             == 'BULBOUS')
            booleanList.add(stalkSurfaceAboveRing == 'SMOOTH')
            booleanList.add(stalkSurfaceBelowRing == 'SMOOTH')
            booleanList.add(stalkColorAboveRing   == 'WHITE')
            booleanList.add(stalkColorBelowRing   == 'WHITE')
            booleanList.add(veilType              == 'PARTIAL')
            booleanList.add(veilColor             == 'WHITE')
            booleanList.add(ringNumber            == 'ONE')
            booleanList.add(ringType              == 'PENDANT')
            booleanList.add(sporePrintColor       == 'PURPLE')
            booleanList.add(population            == 'SEVERAL')
            booleanList.add(habitat               == 'WOODS')

            this.attributeVector = booleanList
                    .stream()
                    .map({x -> x ? 1 : 0})
                    .collect(Collectors.toList())
        }

        attributeVector
    }

    int getAt(int index) {
        getAttributeVector()[index]
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Mushroom mushroom = (Mushroom) o

        if (id != mushroom.id) return false

        return true
    }

    int hashCode() {
        return id
    }

    @Override
    String toString() {
        return """\
Mushroom{
    id=$id,
    edible=$edible,
    capShape='$capShape',
    capSurface='$capSurface',
    capColor='$capColor',
    bruises='$bruises',
    odor='$odor',
    gillAttachment='$gillAttachment',
    gillSpacing='$gillSpacing',
    gillSize='$gillSize',
    gillColor='$gillColor',
    stalkShape='$stalkShape',
    stalkRoot='$stalkRoot',
    stalkSurfaceAboveRing='$stalkSurfaceAboveRing',
    stalkSurfaceBelowRing='$stalkSurfaceBelowRing',
    stalkColorAboveRing='$stalkColorAboveRing',
    stalkColorBelowRing='$stalkColorBelowRing',
    veilType='$veilType',
    veilColor='$veilColor',
    ringNumber='$ringNumber',
    ringType='$ringType',
    sporePrintColor='$sporePrintColor',
    population='$population',
    habitat='$habitat'
}"""
    }
}
