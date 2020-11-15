package taco;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

//@RunWith(SpringRunner.class)
//@WebMvcTest(HomeController.class)  //针对 HomeController的web测试
public class HomeControllerTest {
//
//  @Autowired
//  private MockMvc mockMvc;   //注入MockMvc
//
//  @org.junit.jupiter.api.Test
//  public void testHomePage() throws Exception {
//    mockMvc.perform(get("/"))    //发起对"/home"的get请求
//      .andExpect(status().isOk())    //期望得到http200
//      .andExpect(view().name("home"))  //期望得到home视图
//      .andExpect(content().string(containsString("Welcome to...")));  //期望包含"Welcome to..."
//  }

}
