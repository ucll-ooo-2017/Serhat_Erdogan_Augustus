//@author Serhat Erdogan

package model.db;

import java.util.ArrayList;

import model.domain.Category;

public class Categories {

	private ArrayList<Category> categories;

	public Categories() {
		this.categories = new ArrayList<Category>();
	}

	public void addCategorie(Category categorie) {

		if (categorie == null) {
			throw new DbException("The categorie can not be null!");
		} else {
			for (Category cat : this.categories) {
				if (cat.getTitle().equalsIgnoreCase(categorie.getTitle())) {
					throw new DbException("The categorie name is already used!");
				}
			}
		}
		this.categories.add(categorie);
	}

	public void EditCategorie(String oldName, String oldDescription, Category category) {
		ArrayList<Category> categories2 = new ArrayList<>();
		for (Category cat1 : categories) {
			categories2.add(cat1);
		}
		if (category == null) {
			throw new DbException("The categorie can not be null!");
		} else {
			for (int i = 0; i < this.categories.size(); i++) {
				if (categories.get(i).getTitle().equals(oldName)&& categories.get(i).getDescription().equals(oldDescription)) {

					categories2.remove(i);
					for (Category cat : categories2) {
						if (cat.getTitle().equalsIgnoreCase(category.getTitle())) {
							categories2.add(i, this.getCategory(oldName));
							throw new DbException("The categorie name is already used!");
						}
					}
					categories2.add(i, category);
					categories.remove(i);
					categories.add(i, category);
				}
			}
		}
	}

	public void deleteCategorie(Category categorie) {
		this.categories.remove(categorie.getTitle());
	}

	public Category getCategory(String name) {

		Category result = null;
		for (Category cat : this.categories) {
			if (cat.getTitle() == name) {
				result = cat;
			}
		}
		if (result == null) {
			throw new DbException("Categorie was not found!");
		} else {
			return result;
		}
	}

	public int getSizeCategories() {
		return this.categories.size();
	}

	public ArrayList<Category> getCategories() {
		return categories;
	}
}
