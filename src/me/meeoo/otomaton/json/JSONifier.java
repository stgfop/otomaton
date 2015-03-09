/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.meeoo.otomaton.json;

import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duncan.berenguier
 */
public class JSONifier {

    public static StringBuilder toJSON(StringBuilder sb, Collection<Object> objects) {
        sb.append('[');
        int i = 0;
        for (Object object : objects) {
            toJSON(sb, object);
            if (++i < objects.size()) {
                sb.append(',');
            }
        }
        sb.append(']');
        return sb;
    }

    public static StringBuilder toJSON(StringBuilder sb, Map<Object, Object> objects) {
        sb.append('{');
        int i = 0;
        for (Map.Entry<Object, Object> entry : objects.entrySet()) {
            sb.append('"');
            sb.append(entry.getKey().toString());
            sb.append("\":");
            toJSON(sb, entry.getValue());
            if (++i < objects.size()) {
                sb.append(',');
            }
        }
        sb.append('}');
        return sb;
    }

    public static StringBuilder toJSON(StringBuilder sb, Object object) {
        if (object == null) {
            sb.append("null");
        } else if (object instanceof String) {
            sb.append('"');
            sb.append(object.toString());
            sb.append('"');
        } else if (object instanceof JSONable) {
            ((JSONable) object).toJSON(sb);
        } else if (object instanceof Map) {
            toJSON(sb, ((Map) object));
        } else if (object instanceof Collection) {
            toJSON(sb, ((Collection) object));
        } else if (object instanceof Byte
                || object instanceof Short
                || object instanceof Integer
                || object instanceof Long
                || object instanceof Character
                || object instanceof Float
                || object instanceof Double) {
            sb.append(object.toString());
        } else {
            sb.append('"');
            sb.append(object.toString());
            sb.append('"');
            Logger.getLogger(JSONifier.class.getSimpleName()).log(Level.WARNING, "Object of class {0} written with toString in json", object.getClass());
        }
        return sb;
    }
}
