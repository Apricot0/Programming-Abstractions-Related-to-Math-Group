package geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RadialGraphSymmetries implements Symmetries<RadialGraph>{
    @Override
    public boolean areSymmetric(RadialGraph s1, RadialGraph s2) {
        for(int i=0; i<s1.neighbors.size(); i++){
            if (s1.neighbors.get(i).x!=s2.neighbors.get(i).x || s1.neighbors.get(i).y!=s2.neighbors.get(i).y){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<RadialGraph> symmetriesOf(RadialGraph radialGraph) {
        List<RadialGraph> symmetirs = new ArrayList<>();
        for (int i = 0; i <=359; i++){
            RadialGraphSymmetries graphSymmetries = new RadialGraphSymmetries();
            if (graphSymmetries.areSymmetric(radialGraph,radialGraph.rotateBy(i))){
                symmetirs.add(radialGraph.rotateBy(i));
            }
        }
        return symmetirs;
    }
}
