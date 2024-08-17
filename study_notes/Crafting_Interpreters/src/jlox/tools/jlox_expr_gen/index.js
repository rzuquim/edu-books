import { readFileSync, writeFileSync } from 'fs'
import { fileURLToPath } from 'url'
import path from 'path'

import Handlebars from 'handlebars'
import { program } from 'commander'

const __filename = fileURLToPath(import.meta.url)
const __dirname = path.dirname(__filename)

program
    .version('1.0.0')
    .description('Generates the java boilerplate to the Expr class in jlox')
    .requiredOption('-o, --output <output file>', 'Path to the output file', './Expr.java')
    .parse(process.argv)

let opts = program.opts()

let templatePath = path.join(__dirname, 'assets', 'template.hbs')
let templateSource = readFileSync(templatePath, 'utf8')
let template = Handlebars.compile(templateSource)

let descriptionPath = path.join(__dirname, 'assets', 'description.json')
let description = JSON.parse(readFileSync(descriptionPath, 'utf8'))

let result = template(description)
let outputPath = path.resolve(opts.output)
writeFileSync(outputPath, result, 'utf8')
