package de.fi105.nachweiseBackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ServiceException extends RuntimeException {

    private final int status;

    private final Object content;

}
