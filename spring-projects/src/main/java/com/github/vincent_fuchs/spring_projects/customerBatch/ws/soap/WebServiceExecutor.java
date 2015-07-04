package com.github.vincent_fuchs.spring_projects.customerBatch.ws.soap;

public interface WebServiceExecutor<S,O> {
	O execute(S service, Object...objects);
}
