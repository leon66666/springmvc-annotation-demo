package zhongqiu.springmvc_annotation_demo.test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import zhongqiu.springmvc_annotation_demo.domain.Model;
import zhongqiu.springmvc_annotation_demo.domain.User;

//http://www.cnblogs.com/hoojo/archive/2011/04/22/2024628.html
public class JacksonDemo {
	public static void main(String[] args) throws Exception {
		objectToJson();
		jsonToObject();
		fullDataBinding();
		rawDataBinding();
		genericDataBinding();
		treeModelBinding();
	}

	// 对象转json
	public static void objectToJson() throws ParseException, IOException {
		User user = new User();
		user.setUsername("小民");
		user.setAddress("xiaomin@sina.com");
		user.setSex("男");
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		user.setBirthday(dateformat.parse("1996-10-01"));

		/**
		 * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。
		 * ObjectMapper有多个JSON序列化的方法，可以把JSON字符串保存File、OutputStream等不同的介质中。
		 * writeValue(File arg0, Object arg1)把arg1转成json序列，并保存到arg0文件中。
		 * writeValue(OutputStream arg0, Object arg1)把arg1转成json序列，并保存到arg0输出流中。
		 * writeValueAsBytes(Object arg0)把arg0转成json序列，并把结果输出成字节数组。
		 * writeValueAsString(Object arg0)把arg0转成json序列，并把结果输出成字符串。
		 */
		ObjectMapper mapper = new ObjectMapper();

		// User类转JSON
		// 输出结果：{"name":"小民","age":20,"birthday":844099200000,"email":"xiaomin@sina.com"}
		String json = mapper.writeValueAsString(user);
		System.out.println(json);

		// Java集合转JSON
		// 输出结果：[{"name":"小民","age":20,"birthday":844099200000,"email":"xiaomin@sina.com"}]
		List<User> users = new ArrayList<User>();
		users.add(user);
		String jsonlist = mapper.writeValueAsString(users);
		System.out.println(jsonlist);
	}

	// json转对象
	public static void jsonToObject() throws JsonParseException, JsonMappingException, IOException {
		String json = "{\"username\":\"小民\",\"sex\":20,\"birthday\":844099200000,\"address\":\"xiaomin@sina.com\"}";
		/**
		 * ObjectMapper支持从byte[]、File、InputStream、字符串等数据的JSON反序列化。
		 */
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(json, User.class);
		System.out.println(user.getUsername());
	}

	private static final String MODEL_BINDING = "{\"name\":\"name1\",\"type\":1}";

	// 三种方式
	// fullDataBinding
	public static void fullDataBinding() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Model user = mapper.readValue(MODEL_BINDING, Model.class);// readValue到一个实体类中.
		System.out.println(user.getName());
		System.out.println(user.getType());
	}

	// rawDataBinding
	public static void rawDataBinding() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		HashMap map = mapper.readValue(MODEL_BINDING, HashMap.class);// readValue到一个原始数据类型.
		System.out.println(map.get("name"));
		System.out.println(map.get("type"));
	}

	// genericDataBinding
	private static final String GENERIC_BINDING = "{\"key1\":{\"name\":\"name2\",\"type\":2},\"key2\":{\"name\":\"name3\",\"type\":3}}";

	public static void genericDataBinding() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Model> modelMap = mapper.readValue(GENERIC_BINDING,
				new TypeReference<HashMap<String, Model>>() {
				});// readValue到一个范型数据中.
		Model model = modelMap.get("key2");
		System.out.println(model.getName());
		System.out.println(model.getType());
	}

	private static final String TREE_MODEL_BINDING = "{\"treekey1\":\"treevalue1\",\"treekey2\":\"treevalue2\",\"children\":[{\"childkey1\":\"childkey1\"}]}";

	// Tree Model：最灵活。
	public static void treeModelBinding() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(TREE_MODEL_BINDING);
		// path与get作用相同,但是当找不到该节点的时候,返回missing node而不是Null.
		String treekey2value = rootNode.path("treekey2").textValue();//
		System.out.println("treekey2value:" + treekey2value);
		JsonNode childrenNode = rootNode.path("children");
		String childkey1Value = childrenNode.get(0).path("childkey1").textValue();
		System.out.println("childkey1Value:" + childkey1Value);

		// 创建根节点
		ObjectNode root = mapper.createObjectNode();
		// 创建子节点1
		ObjectNode node1 = mapper.createObjectNode();
		node1.put("nodekey1", 1);
		node1.put("nodekey2", 2);
		// 绑定子节点1
		root.put("child", node1);
		// 数组节点
		ArrayNode arrayNode = mapper.createArrayNode();
		arrayNode.add(node1);
		arrayNode.add(1);
		// 绑定数组节点
		root.put("arraynode", arrayNode);
		// JSON读到树节点
		JsonNode valueToTreeNode = mapper.valueToTree(TREE_MODEL_BINDING);
		// 绑定JSON节点
		root.put("valuetotreenode", valueToTreeNode);
		// JSON绑定到JSON节点对象
		JsonNode bindJsonNode = mapper.readValue(GENERIC_BINDING, JsonNode.class);// 绑定JSON到JSON节点对象.
		// 绑定JSON节点
		root.put("bindJsonNode", bindJsonNode);
		System.out.println(mapper.writeValueAsString(root));
	}
}
