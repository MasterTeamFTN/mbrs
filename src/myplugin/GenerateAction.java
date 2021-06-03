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
import myplugin.generator.EJBGenerator;
import myplugin.generator.RepositoryGenerator;
import myplugin.generator.ModelGenerator;
import myplugin.generator.ServiceGenerator;
import myplugin.generator.ControllerGenerator;
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

		try {
			generateModel(analyzer, root, generatorOptions);
			generateRepositories(analyzer, root, generatorOptions);
			generateServices(analyzer, root, generatorOptions);
			generateControllers(analyzer, root, generatorOptions);
		} catch (AnalyzeException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void generateModel(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "uns.ftn.mbrs.model");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ModelLayerGenerator");
		ModelGenerator generator = new ModelGenerator(generatorOptions);
		generator.generate();
		JOptionPane.showMessageDialog(null, "(MODEL): Code is successfully generated! Generated code is in folder: "
				+ generatorOptions.getOutputPath() + ", package: " + generatorOptions.getFilePackage());
		exportToXml();
	}

	private void generateRepositories(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions)
			throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "uns.ftn.mbrs.repository");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("RepositoryGenerator");
		RepositoryGenerator repositoryGenerator = new RepositoryGenerator(generatorOptions);
		repositoryGenerator.generate();
		JOptionPane.showMessageDialog(null, "(REPOSITORY): Code is successfully generated! Generated code is in folder: " + generatorOptions.getOutputPath() +
				", package: " + generatorOptions.getFilePackage());
		exportToXml();
	}

	private void generateServices(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions)
		throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "uns.ftn.mbrs.service");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ServiceLayerGenerator");
		ServiceGenerator serviceGenerator = new ServiceGenerator(generatorOptions);
		serviceGenerator.generate();
		JOptionPane.showMessageDialog(null, "(SERVICE): Code is successfully generated! Generated code is in folder: " + generatorOptions.getOutputPath() +
				", package: " + generatorOptions.getFilePackage());
		exportToXml();
	}

	private void generateControllers(ModelAnalyzer analyzer, Package root, GeneratorOptions generatorOptions)
		throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "uns.ftn.mbrs.controller");
		analyzer.prepareModel();
		generatorOptions = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ControllerLayerGenerator");
		ControllerGenerator controllerGenerator = new ControllerGenerator(generatorOptions);
		controllerGenerator.generate();
		JOptionPane.showMessageDialog(null, "(CONTROLLER): Code is successfully generated! Generated code is in folder: " + generatorOptions.getOutputPath() +
				", package: " + generatorOptions.getFilePackage());
		exportToXml();
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
