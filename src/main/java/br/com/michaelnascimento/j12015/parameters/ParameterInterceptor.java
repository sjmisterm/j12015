package br.com.michaelnascimento.j12015.parameters;

import java.lang.reflect.Parameter;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
public class ParameterInterceptor {
	@AroundInvoke
	public Object around(InvocationContext ctx) throws Exception {
		for (Parameter p : ctx.getMethod().getParameters()) {
			String name = p.getName();
			
			if (p.isImplicit()) {
				//do something
			}
		}
		
		return ctx.proceed();
	}
}
