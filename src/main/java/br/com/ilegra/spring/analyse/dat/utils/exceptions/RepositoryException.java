package br.com.ilegra.spring.analyse.dat.utils.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RepositoryException extends Exception {
    public RepositoryException(String s) {
        super(s);
    }
}
