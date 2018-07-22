package io.github.viscent.mtpattern.ch10.tss.example.memoryleak;

import io.github.viscent.mtpattern.ch10.tss.ManagedThreadLocal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MemoryLeakPreventingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final ManagedThreadLocal<Counter> TL_COUNTER= ManagedThreadLocal
            .newInstance(new ManagedThreadLocal.InitialValueProvider<Counter>(){
                @Override
                protected Counter initialValue(){
                    return new Counter();
                }
            });

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {

        PrintWriter pwr=response.getWriter();
        pwr.write(TL_COUNTER.get().getAndIncrement());
        pwr.close();

    }
}
