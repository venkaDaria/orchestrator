public class Demo {
   public static void main(String[] args) {
	   ImageReference ref;
	   String[] arr = new String[] {
		   "docker-registry.cloud.sophos/haproxy:dev",
		   "docker-registry.cloud.sophos/haproxy@sha256:123abc",
		   "docker-registry.cloud.sophos/haproxy:dev@sha256:123abc",
	   };
	   for (String el : arr) {
		   ref = new ImageReference(el);
		   System.out.println(ref);
	   }
   }
}
