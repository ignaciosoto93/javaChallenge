package com.marb.framework.api.translator;

public interface Translator<D, O> {

	O dtoToObject(D dto);

	D objectToDto(O object);

}
