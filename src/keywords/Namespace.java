package keywords;

import com.cpp.GeneratorType;

import java.util.List;

/**
 * Created by Rando on 6/27/2016.
 */
public class Namespace extends Keyword{

    private Namespace mChildNamespace;

    public Namespace(String keyword) {
        super(keyword);
    }

    public static Namespace read(String currentLine) {
        String line = currentLine.replace("{","");
        List<String> words = Keyword.getWords(line," ");
        if(words.size() > 1){
            String name = words.get(1);
            Namespace namespace = new Namespace(name);
            return namespace;
        }else {
            return null;
        }


    }

    public void setChild(Namespace namespace) {
        mChildNamespace = namespace;
    }

    public Namespace getNamespace(){
        return mChildNamespace;
    }

    public String getQualifiedName(){
        if(mChildNamespace != null){
            return getName() + "::" + mChildNamespace.getQualifiedName();
        }else {
            return getName();
        }
    }

    public String generate(GeneratorType generatorType){
        if(generatorType == GeneratorType.CXX){
            String name = getQualifiedName();
            StringBuilder stringBuilder = new StringBuilder();
            return "namespace" + " " + name + "{";
        }else {
            return "";
        }
    }

}
