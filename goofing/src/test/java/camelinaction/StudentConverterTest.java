package camelinaction;


import camelinaction.pojo.Student;
import camelinaction.pojo.Teacher;
import junit.framework.TestCase;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;


public class StudentConverterTest extends TestCase {

    public void testStudentConverter() throws Exception {

        CamelContext context = new DefaultCamelContext();

        /*context.addRoutes((RouteBuilder) () -> {
            from("direct:start").convertBodyTo(Teacher.class);
        });*/

        context.start();
        ProducerTemplate template = context.createProducerTemplate();

        Student student = new Student("Jhon Smith", 1);
        Teacher teacher5 = new Teacher("Gnom", 5, 5);
        Teacher teacher = template.requestBody("direct:start", teacher5, Teacher.class);

        //assertNotNull(teacher);
        //System.out.println(teacher.toString());


    }

}