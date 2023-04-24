package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SharedMemory {
    private final List<Token> tokens;
    private int totalTokens;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SharedMemory{");
        sb.append("tokens=").append(tokens);
        sb.append(", totalTokens=").append(totalTokens);
        sb.append('}');
        return sb.toString();
    }


    public SharedMemory(int n) {
        tokens = new ArrayList<>();
        for(int i=0; i < n*n*n; i++){
            Token token = new Token(i);
            tokens.add(token);
            totalTokens = totalTokens + token.getNumber();
        }
        Collections.shuffle(tokens);
    }

    public synchronized List<Token> extractTokens(int howMany) {
        List<Token> extracted = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            if (tokens.isEmpty()) {
                break;
            }
            extracted.add(tokens.remove(0));
        }
        return extracted;
    }

    public List<Token> getTokens() {
        return tokens;
    }
}
