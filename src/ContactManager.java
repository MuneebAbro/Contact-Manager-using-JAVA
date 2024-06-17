import javax.swing.JTextArea;

public class ContactManager {
    private TreeNode root;

    public ContactManager() {
        root = null;
    }

    public void insert(Contact contact) {
        root = insertRec(root, contact);
    }

    private TreeNode insertRec(TreeNode root, Contact contact) {
        if (root == null) {
            root = new TreeNode(contact);
            return root;
        }

        if (contact.name.compareTo(root.contact.name) < 0) {
            root.left = insertRec(root.left, contact);
        } else if (contact.name.compareTo(root.contact.name) > 0) {
            root.right = insertRec(root.right, contact);
        }

        return root;
    }

    public Contact search(String name) {
        TreeNode node = searchRec(root, name);
        return node != null ? node.contact : null;
    }

    private TreeNode searchRec(TreeNode root, String name) {
        if (root == null || root.contact.name.equals(name)) {
            return root;
        }

        if (name.compareTo(root.contact.name) < 0) {
            return searchRec(root.left, name);
        }

        return searchRec(root.right, name);
    }

    public void delete(String name) {
        root = deleteRec(root, name);
    }

    private TreeNode deleteRec(TreeNode root, String name) {
        if (root == null) {
            return root;
        }

        if (name.compareTo(root.contact.name) < 0) {
            root.left = deleteRec(root.left, name);
        } else if (name.compareTo(root.contact.name) > 0) {
            root.right = deleteRec(root.right, name);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.contact = minValue(root.right).contact;
            root.right = deleteRec(root.right, root.contact.name);
        }

        return root;
    }

    private TreeNode minValue(TreeNode root) {
        TreeNode minNode = root;
        while (minNode.left != null) {
            minNode = minNode.left;
        }
        return minNode;
    }

    public void displayContacts(JTextArea outputArea) {
        outputArea.setText(""); // Clear previous content
        outputArea.append("Contacts:\n");
        inOrderTraversal(outputArea, root);
    }

    private void inOrderTraversal(JTextArea outputArea, TreeNode root) {
        if (root != null) {
            inOrderTraversal(outputArea, root.left);
            outputArea.append(root.contact.toString() + "\n");
            inOrderTraversal(outputArea, root.right);
        }
    }
}