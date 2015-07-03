package org.sample.demo;

public interface WebServiceExecutor<S,O> {
	O execute(S service, Object...objects);
}
