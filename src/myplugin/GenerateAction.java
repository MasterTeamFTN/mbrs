package myplugin;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import myplugin.analyzer.AnalyzeException;
import myplugin.analyzer.ModelAnalyzer;
import myplugin.generator.*;

import myplugin.generator.DetailJspGenerator;
import myplugin.generator.HomeJspGenerator;
import myplugin.generator.JspGenerator;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.options.GeneratorOptions;
import myplugin.generator.options.ProjectOptions;

/** Action that activate code generation */
@SuppressWarnings("serial")
class GenerateAction extends MDAction{


	public GenerateAction(String name) {
		super("", name, null, null);
	}

	public void actionPerformed(ActionEvent evt) {

		if (Application.getInstance().getProject() == null) return;
		Package root = Application.getInstance().getProject().getModel();

		if (root == null) return;

		ModelAnalyzer analyzer = null;
		GeneratorOptions generatorOptions = null;

		String packageName = choosePackageName();
		String outputPath = chooseOutputPath();
		String javaOutputPath = outputPath + "/src/main/java";
		String templatesOutputpath = outputPath + "/src/main/resources";

		try {
			generateModel(analyzer, root, generatorOptions, packageName, javaOutputPath);
			generateRepositories(analyzer, root, generatorOptions, packageName, javaOutputPath);
			generateServices(analyzer, root, generatorOptions, packageName, javaOutputPath);
			generateControllers(analyzer, root, generatorOptions, packageName, javaOutputPath);

			generateCss(analyzer, root, generatorOptions, templatesOutputpath);
			generateIndexFreemarker(analyzer, root, generatorOptions, templatesOutputpath);
			generateListFreemarker(analyzer, root, generatorOptions, templatesOutputpath);
			generateDetailsFreemarker(analyzer, root, generatorOptions, templatesOutputpath);
			generateEditFreemarker(analyzer, root, generatorOptions, templatesOutputpath);
			generateCreateFreemarker(analyzer, root, generatorOptions, templatesOutputpath);

			JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
					+ outputPath + ", package: " + packageName);
		} catch (AnalyzeException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private String choosePackageName() {
		return JOptionPane.showInputDialog("Enter package name");
	}

	private String chooseOutputPath() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = chooser.showOpenDialog(null);
		String path = "";

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			path = chooser.getSelectedFile().getAbsolutePath();
		}

		return path;
	}

	private void generateModel(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions, String packageName, String outputPath)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, packageName + ".model");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ModelLayerGenerator");
		ModelGenerator generator = new ModelGenerator(generatorOptions, outputPath);
		generator.generate();
//		JOptionPane.showMessageDialog(null, "(MODEL): Code is successfully generated! Generated code is in folder: "
//				+ generatorOptions.getOutputPath() + ", package: " + generatorOptions.getFilePackage());
		// exportToXml();
	}

	private void generateRepositories(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions, String packageName, String outputPath)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, packageName + ".repository");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("RepositoryGenerator");
		RepositoryGenerator repositoryGenerator = new RepositoryGenerator(generatorOptions, outputPath);
		repositoryGenerator.generate();
//		JOptionPane.showMessageDialog(null, "(REPOSITORY): Code is successfully generated! Generated code is in folder: " + generatorOptions.getOutputPath() +
//				", package: " + generatorOptions.getFilePackage());
//		exportToXml();
	}

	private void generateServices(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions, String packageName, String outputPath)
		throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, packageName + ".service");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ServiceLayerGenerator");
		ServiceGenerator serviceGenerator = new ServiceGenerator(generatorOptions, outputPath);
		serviceGenerator.generate();
//		JOptionPane.showMessageDialog(null, "(SERVICE): Code is successfully generated! Generated code is in folder: " + generatorOptions.getOutputPath() +
//				", package: " + generatorOptions.getFilePackage());
//		exportToXml();
	}

	private void generateControllers(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions, String packageName, String outputPath)
		throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, packageName + ".controller");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ControllerLayerGenerator");
		ControllerGenerator controllerGenerator = new ControllerGenerator(generatorOptions, outputPath);
		controllerGenerator.generate();
	}

	private void generateIndexFreemarker(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions, String outputPath)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "templates");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("IndexFreemarkerGenerator");
		IndexFreemarkerGenerator controllerGenerator = new IndexFreemarkerGenerator(generatorOptions, outputPath);
		controllerGenerator.generate();
//		JOptionPane.showMessageDialog(null, "(INDEX FTL): Code is successfully generated! Generated code is in folder: " + generatorOptions.getOutputPath() +
//				", package: " + generatorOptions.getFilePackage());
//		exportToXml();
	}

	private void generateListFreemarker(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions, String outputPath)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "templates");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ListFreemarkerGenerator");
		ListFreemarkerGenerator controllerGenerator = new ListFreemarkerGenerator(generatorOptions, outputPath);
		controllerGenerator.generate();
//		JOptionPane.showMessageDialog(null, "(LIST FTL): Code is successfully generated! Generated code is in folder: " + generatorOptions.getOutputPath() +
//				", package: " + generatorOptions.getFilePackage());
//		exportToXml();
	}

	private void generateDetailsFreemarker(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions, String outputPath)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "templates");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("DetailFreemarkerGenerator");
		DetailFreemarkerGenerator controllerGenerator = new DetailFreemarkerGenerator(generatorOptions, outputPath);
		controllerGenerator.generate();
//		JOptionPane.showMessageDialog(null, "(DETAIL FTL): Code is successfully generated! Generated code is in folder: " + generatorOptions.getOutputPath() +
//				", package: " + generatorOptions.getFilePackage());
//		exportToXml();
	}
	private void generateEditFreemarker(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions, String outputPath)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "templates");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("EditFreemarkerGenerator");
		EditFreemarkerGenerator controllerGenerator = new EditFreemarkerGenerator(generatorOptions, outputPath);
		controllerGenerator.generate();
//		JOptionPane.showMessageDialog(null, "(DETAIL FTL): Code is successfully generated! Generated code is in folder: " + generatorOptions.getOutputPath() +
//				", package: " + generatorOptions.getFilePackage());
//		exportToXml();
	}
	private void generateCreateFreemarker(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions, String outputPath)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "templates");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("CreateFreemarkerGenerator");
		CreateFreemarkerGenerator controllerGenerator = new CreateFreemarkerGenerator(generatorOptions, outputPath);
		controllerGenerator.generate();
//		JOptionPane.showMessageDialog(null, "(DETAIL FTL): Code is successfully generated! Generated code is in folder: " + generatorOptions.getOutputPath() +
//				", package: " + generatorOptions.getFilePackage());
//		exportToXml();
	}

	private void generateCss(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions, String outputPath)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "templates");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("CssGenerator");
		CssGenerator cssGenerator = new CssGenerator(generatorOptions, outputPath);
		cssGenerator.generate();
	}

	private void exportToXml() {
		if (JOptionPane.showConfirmDialog(null, "Do you want to save FM Model?") ==
				JOptionPane.OK_OPTION)
		{
			JFileChooser jfc = new JFileChooser();
			if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				String fileName = jfc.getSelectedFile().getAbsolutePath();

				XStream xstream = new XStream(new DomDriver());
				BufferedWriter out;
				try {
					out = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(fileName), "UTF8"));
					xstream.toXML(FMModel.getInstance().getClasses(), out);
					xstream.toXML(FMModel.getInstance().getEnumerations(), out);

				} catch (UnsupportedEncodingException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		}
	}

}
